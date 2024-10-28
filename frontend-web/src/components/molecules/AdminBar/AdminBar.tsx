import { useNavigate, useLocation } from 'react-router-dom';
import { StyledAdminBar } from '../AdminBar/AdminBar.style';
import { PATH } from '@/constants/path';

const tabBarInfo = [
  { text: 'FAQ', path: PATH.FAQ },
  { text: '문의사항', path: PATH.QUESTION },
  { text: '데이터 복구', path: PATH.RESTORE },
];

export default function TabBar() {
  const navigate = useNavigate();
  const location = useLocation();

  const navigatePage = (path: string) => {
    navigate(path);
  };

  return (
    <StyledAdminBar>
      {tabBarInfo.map(({ text, path }) => (
        <div
          key={path}
          onClick={() => navigatePage(path)}
          style={{
            color: location.pathname.includes(path) ? '#f9b7b7' : 'grey',
            cursor: 'pointer',
            width: '80px',
            display: 'flex',
            justifyContent: 'center',
          }}
        >
          {text}
        </div>
      ))}
    </StyledAdminBar>
  );
}
