import { registerAs } from '@nestjs/config';

export default registerAs('googleOAuth', () => ({
  clientId: process.env.GOOGLE_CLIENT_ID as string,
  clientSecret: process.env.GOOGLE_CLIENT_SECRET as string,
  callbackUrl: process.env.GOOGLE_CALLBACK_URL as string,
}));
