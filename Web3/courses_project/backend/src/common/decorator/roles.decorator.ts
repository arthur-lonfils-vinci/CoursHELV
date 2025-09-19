import { SetMetadata, applyDecorators } from '@nestjs/common';
import {
  ApiBearerAuth,
  ApiUnauthorizedResponse,
  ApiForbiddenResponse,
} from '@nestjs/swagger';
import { ProjectRole, UserRole } from '@prisma/client';

export interface RolesRequired {
  system?: UserRole[]; // system-wide roles (User.role)
  project?: ProjectRole[]; // per-project roles (ProjectMember.role)
}

export const ROLES_KEY = 'roles';

export const Roles = (roles: RolesRequired) => {
  const decorators = [
    SetMetadata(ROLES_KEY, roles),
    ApiBearerAuth(),
    ApiUnauthorizedResponse({
      description: 'Authentication required - Invalid or missing Bearer token',
    }),
    ApiForbiddenResponse({
      description: `Insufficient permissions. Required roles: ${getRoleDescription(
        roles,
      )}`,
    }),
  ];

  return applyDecorators(...decorators);
};

function getRoleDescription(roles: RolesRequired): string {
  const descriptions: string[] = [];

  if (roles.system?.length) {
    descriptions.push(`System roles: ${roles.system.join(', ')}`);
  }

  if (roles.project?.length) {
    descriptions.push(`Project roles: ${roles.project.join(', ')}`);
  }

  return descriptions.join(' OR ');
}
