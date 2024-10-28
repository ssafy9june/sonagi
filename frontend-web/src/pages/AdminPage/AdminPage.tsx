import { Outlet, Link } from 'react-router-dom';
import {
  AdminContainer,
  Header,
  HeaderImg,
  HeaderTitle,
} from '@/pages/AdminPage/AdminPage.style';
import AdminBar from '@/components/molecules/AdminBar/AdminBar';

const AdminPage = () => {
  return (
    <AdminContainer>
      <Header>
        <HeaderTitle>관리자 페이지</HeaderTitle>
      </Header>
      <AdminBar />
      <Outlet />
    </AdminContainer>
  );
};

export default AdminPage;
