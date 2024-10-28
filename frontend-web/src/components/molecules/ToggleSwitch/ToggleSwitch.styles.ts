import { styled } from 'styled-components';

const StyledLabel = styled.label<{
  checked: boolean;
}>`
  cursor: pointer;
  text-indent: -9999px;
  width: 65px;
  height: 30px;
  background: ${props =>
    props.checked ? props.theme.color.skyblue : props.theme.color.gray3};
  display: block;
  border-radius: 100px;
  position: relative;

  &:after {
    content: '';
    position: absolute;
    left: ${({ checked }) => (checked ? '58%' : '5px')};
    top: 4px;
    width: 22px;
    height: 22px;
    background: #fff;
    border-radius: 100%;
    transition: 0.3s;
  }
`;

export { StyledLabel };
