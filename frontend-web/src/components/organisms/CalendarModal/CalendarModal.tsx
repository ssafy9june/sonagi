import Modal from '@/components/organisms/Modal/Modal';
import moment from 'moment';
import Calendar from 'react-calendar';
import { Value } from 'react-calendar/dist/cjs/shared/types';
import '@/components/organisms/CalendarModal/Calendar.css';

interface CalendarModalProps {
  onModalClose: () => void;
  modalOpen: boolean;
  pickDate: Date;
  onCalendarChange: (value: Value) => void;
}

const CalendarModal = ({
  onModalClose,
  pickDate,
  onCalendarChange,
  modalOpen,
}: CalendarModalProps) => {
  const today = new Date();
  return (
    <Modal height={22} onClose={onModalClose} isOpen={modalOpen}>
      <Calendar
        onClickDay={onModalClose}
        formatDay={(_, date) => moment(date).format('D')}
        minDetail="month" // display only 'Month' unit at navigation
        maxDetail="month" // display only 'Month' unit at navigation
        className="mx-auto w-full text-sm border-b"
        onChange={onCalendarChange}
        value={pickDate}
        maxDate={today}
      />
    </Modal>
  );
};

export { CalendarModal };
