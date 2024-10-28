import { styled } from 'styled-components';

const AdminContainer = styled.div`
  display: flex;
  flex-direction: column;
  height: 100vh;
`;
const Header = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: rgb(254, 229, 229);
  font-size: 23px;
  height: 60px;
  color: #5d5d5d;
`;
const HeaderTitle = styled.div`
  display: flex;
  justify-content: center;
`;
const HeaderImg = styled.div`
  width: 10%;
  display: flex;
  justify-content: right;
  img {
    width: 25px;
  }
`;
const MainContainer = styled.div`
  display: flex;
  flex-direction: column;
`;
const ListContainer = styled.div`
  display: flex;
  justify-content: center;
  overflow: auto;
  max-height: 60vh;
  table {
    width: 95%;
    table-layout: fixed;
    border-collapse: separate;
  }
  th {
    border-bottom: 2px solid #ffcaca;
    color: #5d5d5d;
    height: 50px;
  }
  td,
  th {
    padding: 1em 0.5em;
    text-align: center;
  }
  td {
    border-bottom: 1px solid #e1e1e1;
    text-decoration: none;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }
  a:link {
    text-decoration: none;
  }
  td:nth-child(2) {
    text-align: left;
  }
`;
const QuestionContainer = styled.div`
  display: flex;
  justify-content: center;
  overflow: auto;
  max-height: 85vh;

  table {
    width: 95%;
    table-layout: fixed;
    border-collapse: separate;
    /* max-height: 200px; */
  }
  th {
    border-bottom: 2px solid #ffcaca;
    color: #5d5d5d;
    height: 50px;
  }
  thead {
    position: sticky;
    top: 0;
    background-color: ${({ theme }) => theme.color.white1};
  }

  tbody {
    overflow: auto;
    /* max-height: 30vh; */
  }

  td,
  th {
    padding: 1em 0.5em;
    text-align: center;
  }
  td {
    border-bottom: 1px solid #e1e1e1;
    text-decoration: none;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }
  a:link {
    text-decoration: none;
  }
  td:nth-child(2) {
    text-align: left;
  }
`;
const DetailContainer = styled.div`
  display: flex;
  flex-direction: column;
  margin: 20px auto 0 auto;
  width: 80%;
  a:link {
    text-decoration: none;
  }
`;
const ContentContainer = styled.div`
  display: flex;
  justify-content: center;
  flex-direction: column;

  input,
  textarea {
    border: none;
    width: 100%;
  }
  input {
    font-weight: bold;
    font-size: 20px;
  }
  textarea {
    font-size: 15px;
    min-height: 150px;
  }
`;
const DetailTitle = styled.div`
  text-align: left;
  border-bottom: 2px solid #ffcaca;
  font-weight: bold;
  font-size: 20px;
  padding-bottom: 5px;
`;
const DetailContent = styled.div`
  font-size: 15px;
  margin-top: 10px;
  min-height: 150px;
  margin-bottom: 10px;
`;
const AdminButton = styled.div`
  display: flex;
  justify-content: space-around;
  padding: 0.5rem 1rem;

  font-size: 1rem;
  font-weight: 400;
  border-radius: 5px;
  border: 2px solid pink;
  a:link {
    text-decoration: none;
  }
`;
const AdminButtonContainer = styled.div`
  display: flex;
  justify-content: space-around;
`;
const QuestionDetailContainer = styled.div`
  display: flex;
  justify-content: space-between;
  margin-bottom: 5px;
`;
const RestoreContainer = styled.div`
  display: flex;
  justify-content: center;
  height: 85vh;
  overflow: auto;
  table {
    width: 95%;
    table-layout: fixed;
    border-collapse: separate;
  }
  th {
    text-align: center;
    border-bottom: 2px solid #ffcaca;
    color: #5d5d5d;
    font-size: 14px;
    height: 50px;
  }
  tbody {
    overflow: auto;
  }
  td,
  th {
    padding: 1em 0.5em;
  }
  thead {
    position: sticky;
    top: 0;
    background-color: ${({ theme }) => theme.color.white1};
  }
  td {
    border-bottom: 1px solid #e1e1e1;
    text-decoration: none;
    text-align: center;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }
  a:link {
    text-decoration: none;
  }
  td:nth-child(3),
  td:nth-child(5) {
    font-size: 12px;
  }
`;
const CreateButton = styled.div`
  display: flex;
  justify-content: right;
  margin-top: 10px;
  width: 95%;
  a:link {
    text-decoration: none;
  }
`;
const CategoryConatiner = styled.div`
  display: flex;
  margin-bottom: 10px;
`;
const DetailCategory = styled.div`
  font-size: 15px;
`;
export {
  AdminContainer,
  Header,
  MainContainer,
  ListContainer,
  ContentContainer,
  DetailTitle,
  DetailContent,
  AdminButton,
  AdminButtonContainer,
  QuestionDetailContainer,
  RestoreContainer,
  DetailContainer,
  CreateButton,
  HeaderTitle,
  HeaderImg,
  QuestionContainer,
  CategoryConatiner,
  DetailCategory,
};
