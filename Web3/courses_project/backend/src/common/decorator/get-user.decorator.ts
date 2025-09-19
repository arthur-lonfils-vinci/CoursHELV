import { createParamDecorator, ExecutionContext } from '@nestjs/common';
import { SafeUser } from '../types';

export const GetUser = createParamDecorator(
  (data: string | undefined, ctx: ExecutionContext) => {
    const request = ctx.switchToHttp().getRequest();
    const info: SafeUser = request.user;

    if (data) {
      return info[data];
    }

    return info;
  },
);
