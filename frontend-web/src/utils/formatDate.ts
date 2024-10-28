import moment from 'moment';

export const formatDate = (date: Date) => moment(date).format('YYYY-MM-DD');

export const formatTime = (timeString: string): string => {
  let hour = parseInt(timeString.slice(0, 2), 10);
  const minute = parseInt(timeString.slice(3, 5), 10);

  let period = '';
  if (hour >= 0 && hour < 12) {
    period = 'AM';
  } else if (hour === 12) {
    period = 'PM';
  } else {
    period = 'PM';
    hour -= 12;
  }

  const formattedHour = hour.toString().padStart(2, '0');
  const formattedMinute = minute.toString().padStart(2, '0');

  return `${period} ${formattedHour}:${formattedMinute}`;
};
