import { ReactNode } from 'react';
import ReactDom from 'react-dom';

interface ToastPortalProps {
  children: ReactNode;
}

const ToastPortal = ({ children }: ToastPortalProps) => {
  const el = document.getElementById('toast-root') as HTMLElement;

  return ReactDom.createPortal(children, el);
};

export default ToastPortal;
