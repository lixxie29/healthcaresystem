const development = process.env.NODE_ENV === 'development';
export const url = development ? 'http://localhost:8080/' : '/api/';
export const urlUserData = `user/currentUserSession`;