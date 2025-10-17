import { CanActivate, ExecutionContext, Injectable } from '@nestjs/common';
import { Reflector } from '@nestjs/core';
import { ROLES_KEY } from '../decorator/roles.decorator';
import { handleException } from '../helper';
import { SafeUser } from '../types';
import { ProjectRole, UserRole } from '@prisma/client';
import { PrismaService } from '../../prisma/prisma.service';
import { APP_ERROR_CODE } from '../constant';

@Injectable()
export class RolesGuard implements CanActivate {
  constructor(
    private reflector: Reflector,
    private prisma: PrismaService,
  ) {}

  async canActivate(context: ExecutionContext): Promise<boolean> {
    const roles = this.reflector.getAllAndOverride<{
      system?: UserRole[];
      project?: ProjectRole[];
    }>(ROLES_KEY, [context.getHandler(), context.getClass()]);

    const request = context.switchToHttp().getRequest();
    const user = request.user as SafeUser | undefined;

    // 🔹 1. Check authentication
    if (!user) {
      handleException(APP_ERROR_CODE.UNAUTHORIZED, 'User not authenticated');
    }

    console.log('🛡 Required roles:', roles);
    console.log('🛡 Authenticated user:', { id: user.id, role: user.role });

    // 🔹 2. System role check (User.role)
    if (roles?.system?.length) {
      this.checkRole(
        'System',
        user.role,
        roles.system,
        APP_ERROR_CODE.FORBIDDEN,
      );
    }

    // 🔹 3. Project role check (ProjectMember.role)
    if (roles?.project?.length) {
      const projectId = request.params?.projectId;

      if (!projectId) {
        handleException(
          APP_ERROR_CODE.BAD_REQUEST,
          'Missing projectId in request path',
        );
      }

      const membership = await this.prisma.projectMember.findUnique({
        where: {
          userId_projectId: {
            userId: user.id,
            projectId,
          },
        },
      });

      if (!membership) {
        handleException(
          APP_ERROR_CODE.FORBIDDEN,
          'User is not a member of this project',
        );
      }

      console.log('🛡 Project membership:', membership.role);

      this.checkRole(
        'Project',
        membership.role,
        roles.project,
        APP_ERROR_CODE.FORBIDDEN,
      );
    }

    return true;
  }

  private checkRole<T extends string>(
    scope: 'System' | 'Project',
    currentRole: T,
    allowedRoles: T[],
    errorCode: APP_ERROR_CODE,
  ): void {
    if (!allowedRoles.includes(currentRole)) {
      console.warn(
        `🚫 ${scope} role check failed — allowed: ${allowedRoles.join(
          ', ',
        )}, current: ${currentRole}`,
      );
      handleException(errorCode, `Insufficient ${scope.toLowerCase()} role`);
    } else {
      console.log(`✅ ${scope} role check passed`);
    }
  }
}
