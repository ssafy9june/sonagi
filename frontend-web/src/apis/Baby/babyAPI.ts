import { instance } from '@/apis/instance';
import {
  Baby,
  UpdateBaby,
  UpdateMedicalCheck,
  UpdateVaccination,
} from '@/types';

const addBaby = async (baby: Baby) => {
  try {
    await instance.post('/babyInfos', baby);
  } catch {
    new Error('baby info add error');
  }
};

const changeBabyState = async (babyId: number) => {
  await instance.put(`/babyState/${babyId}`);
};

const getBaby = async (userId: number) => {
  try {
    const response = await instance.get(`/babyInfos/${userId}`);
    return response.data;
  } catch {
    new Error('No data returned from the API');
  }
};

const getCoParent = async (userId: number, babyId: number) => {
  try {
    const response = await instance.get(`/coparents/${userId}/${babyId}`);
    return response.data;
  } catch {
    new Error('get coparent list error');
  }
};

// 아이 세부 정보 불러오기
const getBabyDetail = async (babyId: number, userId: number) => {
  try {
    const response = await instance.get(`/babyDetail/${babyId}/${userId}`);
    return response.data;
  } catch {
    new Error('baby detail get error');
  }
};

const deleteCoparent = async (babyId: number, coparentId: number) => {
  await instance.delete(`/coparents/${babyId}/${coparentId}`);
};

const getBabyCode = async (babyId: number) => {
  try {
    const response = await instance.get(`/babyCode?babyId=${babyId}`);
    return response.data;
  } catch {
    new Error('get baby code error');
  }
};

// 아기 코드로 아기 등록
const registerBabyCode = async (userId: number, code: string) => {
  await instance.post('babyCode', { userId: userId, code: code });
};

// 아이 정보 변경
const updateBaby = async (baby: UpdateBaby) => {
  try {
    await instance.put('/babyDetail', baby);
  } catch {
    new Error('baby name put error');
  }
};

const getVaccination = async (babyId: number) => {
  try {
    const response = await instance.get(`/vaccination/${babyId}`);
    return response.data;
  } catch {
    new Error('get baby vaccination status error');
  }
};

const getMedicalCheck = async (babyId: number) => {
  try {
    const response = await instance.get(`/checkup/${babyId}`);
    return response.data;
  } catch {
    new Error('get baby medical check status error');
  }
};

const getVaccinationDetail = async (babyId: number, vaccinationId: number) => {
  try {
    const response = await instance.get(
      `/vaccination/${babyId}/${vaccinationId}`
    );
    return response.data;
  } catch {
    new Error('get baby vaccination detail error');
  }
};

const getMedicalCheckDetail = async (
  babyId: number,
  medicalCheckId: number
) => {
  try {
    const response = await instance.get(`/checkup/${babyId}/${medicalCheckId}`);
    return response.data;
  } catch {
    new Error('get baby medical check detail error');
  }
};

const updateVaccinationDate = async (vaccinationDate: UpdateVaccination) => {
  try {
    await instance.put('/vaccination', vaccinationDate);
  } catch {
    new Error('update vaccination put error');
  }
};

const updateMedicalCheckDate = async (medicalCheckDate: UpdateMedicalCheck) => {
  try {
    await instance.put('/checkup', medicalCheckDate);
  } catch {
    new Error('update medicalCheck put error');
  }
};

export {
  addBaby,
  getBaby,
  getCoParent,
  deleteCoparent,
  getBabyCode,
  registerBabyCode,
  updateBaby,
  changeBabyState,
  getVaccination,
  getMedicalCheck,
  getBabyDetail,
  getVaccinationDetail,
  getMedicalCheckDetail,
  updateVaccinationDate,
  updateMedicalCheckDate,
};
