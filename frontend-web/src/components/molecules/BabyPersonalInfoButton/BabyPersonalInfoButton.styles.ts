import styled from 'styled-components';

const BabyPersonalInfoButtonContainer = styled.div`
  display: left;
  justify-content: center;
  > button {
    width: 133px;
    height: 164px;
    border-radius: 12px;
    border: 1px solid #a6d6f0;
    background: rgba(255, 255, 255, 0.8);
    white-space: normal;
    word-wrap: break-word;
    padding: 10px;
  }
`;

const NameWrapper = styled.div`
  display: flex;
  text-align: end;
  justify-content: flex-end;
  flex-direction: column;
  height: 110px;
  padding: 10px;
`;

const ShowTextWrapper = styled.div`
  height: 100px;
  margin: 10px;
  overflow: hidden;
  text-overflow: ellipsis;
  -webkit-box-orient: vertical;
  text-align: start;
  /* -webkit-line-clamp: 6; */
`;

export { BabyPersonalInfoButtonContainer, NameWrapper, ShowTextWrapper };
