// cache/cache.module.ts
import { Module } from '@nestjs/common';
import { CacheModule as NestCacheModule } from '@nestjs/cache-manager';
import { RedisOptions } from '../config';
import { CacheService } from './cache.service';

@Module({
  imports: [NestCacheModule.registerAsync(RedisOptions)],
  providers: [CacheService],
  exports: [CacheService, NestCacheModule],
})
export class AppCacheModule {}
