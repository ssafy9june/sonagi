import styled from 'styled-components';

interface BackgroundProps {
  $background: string;
}

const Background = styled.div<BackgroundProps>`
  background-image: url(${props => props.$background});
  background-repeat: no-repeat;
  background-position: top center;
  background-size: 100% 100%;
  height: 100vh;
  background-attachment: fixed;
`;

export { Background };
