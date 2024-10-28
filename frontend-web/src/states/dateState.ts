import moment from 'moment';
import { atom } from 'recoil';

export const selectedDateState = atom<string>({
  key: 'selectedDateState',
  default: moment(new Date()).format('YYYY-MM-DD'),
});
