import { GrapByDayContainer } from '@/components/organisms/GraphByDay/GraphByDay.styles';
import DoughnutChart from '@/components/molecules/DoughnutChart/DoughnutChart';
import { useRecoilValue } from 'recoil';
import { selectedBabyState } from '@/states/babyState';
import { BabiesOfUser, Category } from '@/types';
import { selectedDateState } from '@/states/dateState';
import { selectedCategoryState } from '@/states/categoryState';
import { PATH } from '@/constants/path';
import { DiaperCard } from '@/components/organisms/Card/DiaperCard';
import { SleepCard } from '@/components/organisms/Card/SleepCard';
import { PumpCard } from '@/components/organisms/Card/PumpCard';
import { ActivityCard } from '@/components/organisms/Card/ActivityCard';
import { HealthCard } from '@/components/organisms/Card/HealthCard';
import { ExtraCard } from '@/components/organisms/Card/ExtraCard';
import React, { Suspense, lazy } from 'react';
import LoadingPage from '@/pages/LoadingPage/LoadingPage';
import { useGetAllStatistics } from '@/apis/Statistic/Queries/useGetAllStatistics';

const mealCardPromise = import('@/components/organisms/Card/MealCard');

const MealCard = React.lazy(() =>
  mealCardPromise.then(({ MealCard }) => ({ default: MealCard }))
);

const GraphByDay = () => {
  const currentCategory: Category = useRecoilValue(
    selectedCategoryState(PATH.GRAPH)
  );
  const babyInfo: BabiesOfUser = useRecoilValue(selectedBabyState);
  const date = useRecoilValue(selectedDateState);

  const getGraphDatas = useGetAllStatistics(babyInfo.babyId, 'day', date);
  return (
    <Suspense fallback={<LoadingPage />}>
      <GrapByDayContainer className="scrollable">
        {currentCategory === 'Meal' && (
          <>
            <DoughnutChart data={getGraphDatas[0].data.times} />
            <MealCard data={getGraphDatas[0].data} />
          </>
        )}
        {currentCategory === 'Diaper' && (
          <>
            <DoughnutChart data={getGraphDatas[1].data.times} />
            <DiaperCard data={getGraphDatas[1].data} />
          </>
        )}
        {currentCategory === 'Sleep' && (
          <>
            <DoughnutChart data={getGraphDatas[2].data.times} />
            <SleepCard data={getGraphDatas[2].data} />
          </>
        )}
        {currentCategory === 'Pump' && (
          <>
            <DoughnutChart data={getGraphDatas[3].data.times} />
            <PumpCard data={getGraphDatas[3].data} />
          </>
        )}
        {currentCategory === 'Activity' && (
          <>
            <DoughnutChart data={getGraphDatas[4].data.times} />
            <ActivityCard data={getGraphDatas[4].data} />
          </>
        )}
        {currentCategory === 'Health' && (
          <>
            <DoughnutChart data={getGraphDatas[5].data.times} />
            <HealthCard data={getGraphDatas[5].data} />
          </>
        )}
        {currentCategory === 'Extra' && (
          <>
            <DoughnutChart data={getGraphDatas[6].data.times} />
            <ExtraCard data={getGraphDatas[6].data} />
          </>
        )}
      </GrapByDayContainer>
    </Suspense>
  );
};

export { GraphByDay };
