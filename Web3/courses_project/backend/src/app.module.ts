import { Module } from '@nestjs/common';
import { SecurityService } from './security/security.service';
import { ConfigModule } from '@nestjs/config';
import { ThrottlerGuard, ThrottlerModule } from '@nestjs/throttler';
import { AuthModule } from './auth/auth.module';
import { AppCacheModule } from './cache/cache.module';
import { ThrottlerOptions } from './config';
import { PrismaModule } from './prisma/prisma.module';
import { APP_GUARD } from '@nestjs/core';
import { NoThrottlerTestGuard } from './common/guard';
import { UserModule } from './user/user.module';
import { ProjectController } from './project/project.controller';
import { ProjectService } from './project/project.service';
import { ProjectModule } from './project/project.module';
import { ExpenseController } from './expense/expense.controller';
import { ExpenseService } from './expense/expense.service';
import { ExpenseModule } from './expense/expense.module';
import { UserController } from './user/user.controller';
import { UserService } from './user/user.service';
import { CategoryController } from './category/category.controller';
import { CategoryService } from './category/category.service';
import { CategoryModule } from './category/category.module';

@Module({
  imports: [
    ConfigModule.forRoot({ isGlobal: true }),
    AppCacheModule,
    ThrottlerModule.forRoot(ThrottlerOptions),
    PrismaModule,
    AuthModule,
    UserModule,
    ProjectModule,
    ExpenseModule,
    CategoryModule,
  ],
  controllers: [
    ProjectController,
    ExpenseController,
    UserController,
    CategoryController,
  ],
  providers: [
    {
      provide: APP_GUARD,
      useClass:
        process.env.NODE_ENV === 'test' ? NoThrottlerTestGuard : ThrottlerGuard,
    },
    UserService,
    SecurityService,
    ProjectService,
    ExpenseService,
    CategoryService,
  ],
})
export class AppModule {}
