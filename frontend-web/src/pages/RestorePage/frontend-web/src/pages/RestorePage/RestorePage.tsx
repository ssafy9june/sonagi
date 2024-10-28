import { useEffect, useState } from 'react';
import { Outlet } from 'react-router-dom';
import { DeletedBaby } from '@/types';
import { instance } from '@/apis/instance';
import {
  MainContainer,
  RestoreContainer,
} from '@/pages/AdminPage/AdminPage.style';

const RestorePage = () => {
  const [deletedBabyList, setDeletedBabyList] = useState<DeletedBaby[]>([]);
  const [currentPost, setCurrentPost] = useState<DeletedBaby[]>([]);

  useEffect(() => {
    instance
      .get('/isDeleted')
      .then(response => {
        setDeletedBabyList([...response.data].reverse());
      })
      .catch(function (error) {
        console.log(error);
      });
  }, []);

  useEffect(() => {
    setCurrentPost(deletedBabyList);
  }, [deletedBabyList]);

  const restoreBaby = (babyId: number, name: string) => {
    if (window.confirm(`"${name}"님 정보를 복구하시겠습니까?`)) {
      instance
        .put(`/isDeleted/${babyId}`)
        .then(response => {
          alert('아기 정보가 성공적으로 복구되었습니다.');
          window.location.reload();
        })
        .catch(error => {
          console.error('복구 실패:', error);
        });
    }
  };
  console.log(currentPost);
  return (
    <MainContainer>
      <Outlet />
      <RestoreContainer>
        <table>
          <colgroup>
            <col width="10%" />
            <col width="20%" />
            <col width="20%" />
            <col width="10%" />
            <col width="20%" />
          </colgroup>

          <thead>
            <tr>
              <th>ID</th>
              <th>아이 이름</th>
              <th>생년월일</th>
              <th>성별</th>
              <th>삭제일자</th>
            </tr>
          </thead>

          <tbody>
            {currentPost.map((deletedBaby, index) => (
              <tr
                key={index}
                onClick={() =>
                  restoreBaby(deletedBaby.babyId, deletedBaby.name)
                }
              >
                <td>{deletedBaby.babyId}</td>
                <td>{deletedBaby.name}</td>
                <td>{deletedBaby.birthDate}</td>
                <td>{deletedBaby.gender}</td>
                <td>{deletedBaby.deletedAt}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </RestoreContainer>
    </MainContainer>
  );
};

export default RestorePage;
