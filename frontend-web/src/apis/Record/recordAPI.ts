import { instance } from '@/apis/instance';
import { AllRecords, CombinedRecord } from '@/types/recordTypes';

const getAllCategoryRecords = async (babyId: number, date: string) => {
  try {
    const response = await instance.get(
      `/allRecords?babyId=${babyId}&createdDate=${date}`
    );
    return response.data;
  } catch {
    new Error('no data returned from the API - AllCategory');
  }
};

const getRecordDetails = async (type: string, recordId: number) => {
  try {
    // ex. tummytimes/38
    const response = await instance.get(`/${type}/${recordId}`);
    return response.data;
  } catch {
    new Error('get record code error');
  }
};

const addRecord = async (type: string, record: AllRecords) => {
  try {
    await instance.post(`/${type}`, record);
  } catch {
    throw new Error('record add error');
  }
};

const updateRecord = async (record: CombinedRecord, queryName: string) => {
  try {
    await instance.put(`/${queryName}`, record);
  } catch {
    new Error('record put error');
  }
};

const deleteRecord = async (type: string, recordId: number) => {
  await instance.delete(`/${type}/${recordId}`);
};

export {
  getAllCategoryRecords,
  addRecord,
  updateRecord,
  getRecordDetails,
  deleteRecord,
};
