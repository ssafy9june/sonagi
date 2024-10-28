import { instance } from '@/apis/instance';
import { QuestionPost, User } from '@/types';

const updateUser = async (user: User) => {
  try {
    await instance.put('/name', user);
  } catch {
    new Error('user name put error');
  }
};

const getUserName = async (userId: number) => {
  try {
    const response = await instance.get(`/name/${userId}`);
    return response.data;
  } catch {
    new Error('user name put error');
  }
};

// 알림 전체 상태 get
const getNotification = async (userId: number) => {
  try {
    const response = await instance.get(`/notification/${userId}`);
    return response.data;
  } catch {
    new Error('get user notification state');
  }
};

const updateAlarm = async (
  alarmType: string,
  userId: number,
  alarmState: boolean
) => {
  await instance.put(`/${alarmType}/${userId}/${Number(alarmState)}`);
};

// 로그아웃 요청 보내기
const logout = async () => {
  await instance.post('/logout');
};

// 문의 등록하기
const addQuestion = async (questionPost: QuestionPost) => {
  await instance.post('/questions', questionPost);
};

export {
  updateUser,
  getNotification,
  updateAlarm,
  logout,
  getUserName,
  addQuestion,
};
