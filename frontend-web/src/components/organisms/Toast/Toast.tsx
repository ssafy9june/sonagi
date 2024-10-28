import { Text } from '@/components/atoms/Text/Text.styles';
import { ToastContainer } from '@/components/organisms/Toast/Toast.styles';
import ToastPortal from '@/components/organisms/ToastPortal/ToastPortal';
import theme from '@/styles/theme';
import { useEffect } from 'react';

interface ToastProps {
  message: string;
  setToast: React.Dispatch<React.SetStateAction<boolean>>;
}

const Toast = ({ message, setToast }: ToastProps) => {
  useEffect(() => {
    const timer = setTimeout(() => {
      setToast(false);
    }, 1500);

    return () => {
      clearTimeout(timer);
    };
  }, [setToast]);

  return (
    <ToastPortal>
      <ToastContainer>
        <Text size="medium3" color={theme.color.black3}>
          {message}
        </Text>
      </ToastContainer>
    </ToastPortal>
  );
};

export { Toast };
