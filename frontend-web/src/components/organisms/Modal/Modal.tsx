import React, { ReactNode } from 'react';
import * as S from '@/components/organisms/Modal/Modal.styles';
import ModalPortal from '@/components/organisms/ModalPortal/ModalPortal';

interface ModalProps extends S.ModalStyleProps {
  children: ReactNode;
  onClose?: () => void;
  isOpen: boolean;
}

const Modal = ({
  width = 21,
  height,
  $borderRadius = 16,
  unit = 'rem',
  onClose,
  isOpen,
  children,
}: ModalProps) => {
  return (
    <>
      {isOpen && (
        <ModalPortal>
          <S.ModalBackground onClick={onClose}>
            <S.ModalContainer
              width={width}
              height={height}
              $borderRadius={$borderRadius}
              unit={unit}
              onClick={(e: any) => {
                e.stopPropagation();
              }}
            >
              {children}
            </S.ModalContainer>
          </S.ModalBackground>
        </ModalPortal>
      )}
    </>
  );
};

export default Modal;
