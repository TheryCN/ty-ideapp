import { NOTIFY } from './types';

export const notify = (message) => ({
  type: NOTIFY,
  message
});
