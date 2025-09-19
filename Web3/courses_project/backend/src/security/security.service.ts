import { Injectable, Logger } from '@nestjs/common';

@Injectable()
export class SecurityService {
  private readonly logger = new Logger(SecurityService.name);

  logSecurityEvent(
    event: string,
    userId?: string,
    ip?: string,
    details?: unknown,
  ): void {
    this.logger.warn(`SECURITY: ${event}`, {
      userId,
      ip,
      timestamp: new Date().toISOString(),
      details: JSON.stringify(details),
    });
  }

  logFailedLogin(email: string, ip?: string): void {
    this.logSecurityEvent('FAILED_LOGIN', undefined, ip, { email });
  }

  logSuspiciousActivity(userId: string, activity: string, ip?: string): void {
    this.logSecurityEvent('SUSPICIOUS_ACTIVITY', userId, ip, { activity });
  }
}
