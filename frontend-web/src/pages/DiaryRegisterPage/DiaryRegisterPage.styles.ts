import { Text } from '@/components/atoms/Text/Text.styles';
import styled from 'styled-components';
import { sectorHeight } from '@/constants/doughnutConstants';
import { StyledEngineProvider } from '@mui/styled-engine';

const DiaryRegisterContainer = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 1rem;
  gap: 1rem;
`;

const DiaryRegisterHeadContainer = styled.div`
  width: 100%;
  display: flex;
  position: relative;
  justify-content: center;
  align-items: center;
  box-sizing: border-box;
  padding-left: 1rem;
  padding-right: 1rem;
`;

const DiaryRegisterBodyContainer = styled.div`
  width: 100%;
  box-sizing: border-box;
  padding-left: 1rem;
  padding-right: 1rem;
`;

const DiaryRegisterFileListContainer = styled.div`
  width: 100%;
  height: 20vw;
  display: flex;
  justify-content: start;
  box-sizing: border-box;
  padding-left: 1rem;
  padding-right: 1rem;
  gap: 4%;
`;

const DiaryRegisterWrapper = styled.div`
  position: relative;
  width: 22%;
  border-radius: 12px;
  overflow: hidden;
`;

const RegisterBtnContainer = styled.div`
  display: flex;
  flex-direction: row;
  gap: 0.5rem;
  width: 100%;
  box-sizing: border-box;
  padding-left: 1rem;
  padding-right: 1rem;
`;

const TitleText = styled(Text)`
  color: #616161;
`;

const BackArrow = styled.div`
  display: flex;
  top: calc(sectorHeight);
  left: 5vw;
  /* transform: translate(50%, 0%); */
  position: absolute;
`;

export {
  DiaryRegisterContainer,
  DiaryRegisterHeadContainer,
  DiaryRegisterBodyContainer,
  DiaryRegisterFileListContainer,
  DiaryRegisterWrapper,
  RegisterBtnContainer,
  TitleText,
  BackArrow,
};
