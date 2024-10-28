import {
  getAllStatistics,
  getExtraStatistics,
} from '@/apis/Statistic/statisticApi';
import { PeriodType } from '@/types/card';
import { useQueries, useQuery } from '@tanstack/react-query';

const categoryTypeArr = [
  'meal',
  'diaper',
  'sleep',
  'pumpingBreast',
  'activity',
  'health',
  'extra',
] as const;

// type CategoryType = typeof categoryTypeArr;

const useGetAllStatistics = (
  babyId: number,
  period: PeriodType,
  date: string
) => {
  return useQueries({
    queries: categoryTypeArr.map(cType => {
      return {
        queryKey: ['AllStatistics', cType, babyId, date, period],
        queryFn: () => getAllStatistics(cType, babyId, period, date),
      };
    }),
  });
};

export { useGetAllStatistics };
