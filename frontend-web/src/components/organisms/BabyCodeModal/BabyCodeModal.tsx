import { Text } from '@/components/atoms/Text/Text.styles';
import {
  BabyCodeModalContainer,
  BabyCodeWrapper,
} from '@/components/organisms/BabyCodeModal/BabyCodeModal.styles';
import Modal from '@/components/organisms/Modal/Modal';
import { BabiesOfUser, CustomModal } from '@/types';
import copy from '@/assets/images/icon-copy-gray.png';
import { Image } from '@/components/atoms/Image/Image';
import { useRef, useState } from 'react';
import { Toast } from '@/components/organisms/Toast/Toast';
import { useRecoilValue } from 'recoil';
import { selectedBabyState } from '@/states/babyState';
import { useGetBabyCode } from '@/apis/Baby/Queries/useGetBabyCode';
import kakao from '@/assets/images/img-logo-kakao.png';
import Button from '@/components/atoms/Button/Button';

const BabyCodeModal = ({ onModalClose, modalOpen }: CustomModal) => {
  const babyInfo: BabiesOfUser = useRecoilValue(selectedBabyState);

  const code = useGetBabyCode(babyInfo.babyId);

  const [showToast, setShowToast] = useState<boolean>(false);

  const handleCopyClick = async () => {
    // React Native Webview라면 RN으로 String 전달
    if (window.ReactNativeWebView) {
      window.ReactNativeWebView.postMessage(
        JSON.stringify({
          type: 'BabyCode',
          code: code,
        })
      );
    } else {
      // Web이라면 writeText활용
      try {
        await navigator.clipboard.writeText(code);
        setShowToast(true);
      } catch (error) {
        console.log('복사 실패', error);
      }
    }
  };
  const shareKakao = () => {
    window.Kakao.Link.sendCustom({
      templateId: 97145,
      templateArgs: {
        code: code,
      },
    });
  };

  return (
    <>
      {showToast && (
        <Toast message="아기 코드가 복사되었습니다" setToast={setShowToast} />
      )}
      <Modal isOpen={modalOpen} onClose={onModalClose}>
        <BabyCodeModalContainer>
          <Text size="large">초대 코드</Text>
          <BabyCodeWrapper>
            <Text size="headMedium" $fontWeight={700}>
              {code}
            </Text>
            <Image
              src={copy}
              width={1.5}
              height={1.5}
              onClick={handleCopyClick}
            />
          </BabyCodeWrapper>
          <Button onClick={shareKakao}>
            <Image src={kakao} width={2} />
            <Text size="medium1">
              <b>카카오톡</b>으로 공유하기
            </Text>
          </Button>
          <Button option="activated" size="large" onClick={onModalClose}>
            확인
          </Button>
        </BabyCodeModalContainer>
      </Modal>
    </>
  );
};

export { BabyCodeModal };
