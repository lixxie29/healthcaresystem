const development = process.env.NODE_ENV === 'development';
export const url = development ? 'http://localhost:3000/' : '/api/';
export const urlUserData = `user/currentUserSession`;