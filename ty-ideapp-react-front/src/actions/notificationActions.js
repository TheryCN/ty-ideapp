import { NOTIFY, CLEAR_NOTIFICATION } from './types';

export const notify = (message) => ({
  type: NOTIFY,
  message
});

export const clearNotification = () => ({
  type: CLEAR_NOTIFICATION
});
