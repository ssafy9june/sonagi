import { PeriodType } from '@/types/card';
import { instance } from '@/apis/instance';
// 전체
const getAllStatistics = async (
  categoryType: string,
  babyId: number,
  period: PeriodType,
  date: string
) => {
  try {
    const respose = await instance.get(
      `/${categoryType}Statistics?babyId=${babyId}&period=${period}&createdDate=${date}`
    );
    return respose.data;
  } catch {
    new Error('get meal statistics error');
  }
};

// 식사 통계
const getMealStatistics = async (
  babyId: number,
  period: PeriodType,
  date: string
) => {
  try {
    const respose = await instance.get(
      `/mealStatistics?babyId=${babyId}&period=${period}&createdDate=${date}`
    );
    return respose.data;
  } catch {
    new Error('get meal statistics error');
  }
};

// 배변 통계
const getDiaperStatistics = async (
  babyId: number,
  period: PeriodType,
  date: string
) => {
  try {
    const respose = await instance.get(
      `/diaperStatistics?babyId=${babyId}&period=${period}&createdDate=${date}`
    );
    return respose.data;
  } catch {
    new Error('get diaper statistics error');
  }
};

// 수면 통계
const getSleepStatistics = async (
  babyId: number,
  period: PeriodType,
  date: string
) => {
  try {
    const respose = await instance.get(
      `/sleepStatistics?babyId=${babyId}&period=${period}&createdDate=${date}`
    );
    return respose.data;
  } catch {
    new Error('get sleep statistics error');
  }
};

// 유축 통계
const getPumpStatistics = async (
  babyId: number,
  period: PeriodType,
  date: string
) => {
  try {
    const respose = await instance.get(
      `/pumpingBreastStatistics?babyId=${babyId}&period=${period}&createdDate=${date}`
    );
    return respose.data;
  } catch {
    new Error('get pumping breast statistics error');
  }
};

// 놀이 통계
const getActivityStatistics = async (
  babyId: number,
  period: PeriodType,
  date: string
) => {
  try {
    const respose = await instance.get(
      `/activityStatistics?babyId=${babyId}&period=${period}&createdDate=${date}`
    );
    return respose.data;
  } catch {
    new Error('get activity statistics error');
  }
};

// 건강 통계
const getHealthStatistics = async (
  babyId: number,
  period: PeriodType,
  date: string
) => {
  try {
    const respose = await instance.get(
      `/healthStatistics?babyId=${babyId}&period=${period}&createdDate=${date}`
    );
    return respose.data;
  } catch {
    new Error('get health statistics error');
  }
};

// 기타 통계
const getExtraStatistics = async (
  babyId: number,
  period: PeriodType,
  date: string
) => {
  try {
    const respose = await instance.get(
      `/extraStatistics?babyId=${babyId}&period=${period}&createdDate=${date}`
    );
    return respose.data;
  } catch {
    new Error('get extra statistics error');
  }
};
export {
  getAllStatistics,
  getMealStatistics,
  getDiaperStatistics,
  getSleepStatistics,
  getPumpStatistics,
  getHealthStatistics,
  getActivityStatistics,
  getExtraStatistics,
};
