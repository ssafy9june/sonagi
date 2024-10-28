import { styled } from 'styled-components';
import theme from '@/styles/theme';

const MemoArea = styled.textarea`
  padding: 1rem, 0.5rem;
  width: 100%;
  height: 35vh;
  line-height: 1.5;
  /* background: var(--white-1, #fff); */
  font-size: ${theme.fontSize.medium1};
  border: none;
  &:focus {
    border: none;
    outline: none;
  }
  box-sizing: border-box;
  padding: 1rem;
  font-family: 'Happiness-Sans';
  resize: none;
  border: none;
  background: transparent;
`;

const MemoWrapper = styled.div`
  position: relative;
  border-radius: 0.5rem;
  background-color: ${theme.color.lightgrey};
  display: flex;
  flex-direction: column;
  align-items: center;
  box-sizing: border-box;
  padding: 1rem;
`;

const WordCount = styled.span`
  position: absolute;
  bottom: 0;
  right: 0;
  padding: 20px;
  font-size: 1rem;
`;

const MemoTextWrapper = styled.p`
  text-align: left;
  margin-bottom: 20px;
`;

export { MemoArea, MemoWrapper, WordCount, MemoTextWrapper };
