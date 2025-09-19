import { Injectable } from '@nestjs/common';
import { ThrottlerGuard } from '@nestjs/throttler';

@Injectable()
export class NoThrottlerTestGuard extends ThrottlerGuard {
  // eslint-disable-next-line @typescript-eslint/require-await
  protected async handleRequest(): Promise<boolean> {
    // Always allow requests in test mode
    return true;
  }
}
