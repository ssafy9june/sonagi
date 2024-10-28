import { styled } from 'styled-components';

const ToastContainer = styled.div`
  width: max-content;
  padding: 1rem;
  border-radius: 10px;
  display: flex;
  align-items: center;
  justify-content: center;
  position: fixed;
  bottom: 10%;
  left: 50%;
  transform: translate(-50%, 0);
  background: ${({ theme }) => theme.color.black5};

  animation: fadeIn 1s ease-out forwards;
  -moz-animation: fadeIn 1s ease-out forwards;
  -webkit-animation: fadeIn 1s ease-out forwards;

  animation: fadeOut 2s ease-out forwards;
  -moz-animation: fadeOut 2s ease-out forwards;
  -webkit-animation: fadeOut 2s ease-out forwards;
`;

export { ToastContainer };
