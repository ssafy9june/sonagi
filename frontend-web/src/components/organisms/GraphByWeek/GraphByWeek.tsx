import { DiaperBarGraph } from '@/components/molecules/BarChart/Diaper/DiaperBarGraph';
import { MealBarGraph } from '@/components/molecules/BarChart/Meal/MealBarGraph';
import { PumpingBreastBarGraph } from '@/components/molecules/BarChart/PumpingBreast/PumpingBreastBarGraph';
// import { Card } from '@/components/organisms/Card/Card';
import { GraphByWeekContainer } from '@/components/organisms/GraphByWeek/GraphByWeek.style';
import { selectedCategoryState } from '@/states/categoryState';
import { useRecoilValue } from 'recoil';
import { PATH } from '@/constants/path';
import { HealthBarGraph } from '@/components/molecules/BarChart/Health/HealthBarGraph';
import { useGetAllStatistics } from '@/apis/Statistic/Queries/useGetAllStatistics';
import { BabiesOfUser } from '@/types';
import { selectedBabyState } from '@/states/babyState';
import { selectedDateState } from '@/states/dateState';
import { MealCard } from '@/components/organisms/Card/MealCard';
import { DiaperCard } from '@/components/organisms/Card/DiaperCard';
import { PumpCard } from '@/components/organisms/Card/PumpCard';
import { SleepCard } from '@/components/organisms/Card/SleepCard';
import { ActivityCard } from '@/components/organisms/Card/ActivityCard';
import { HealthCard } from '@/components/organisms/Card/HealthCard';
import { ExtraCard } from '@/components/organisms/Card/ExtraCard';
import { ExtraBarGraph } from '@/components/molecules/BarChart/Extra/ExtraBarGraph';
import { SleepBarGraph } from '@/components/molecules/BarChart/Sleep/SleepBarGraph';
import { ActivityBarGraph } from '@/components/molecules/BarChart/Activity/ActivityBarGraph';

const GraphByWeek = () => {
  const currentCategory = useRecoilValue(selectedCategoryState(PATH.GRAPH));

  const babyInfo: BabiesOfUser = useRecoilValue(selectedBabyState);
  const date = useRecoilValue(selectedDateState);

  const getGraphDatas = useGetAllStatistics(babyInfo.babyId, 'week', date);

  return (
    <GraphByWeekContainer className="scrollable">
      {currentCategory === 'Meal' && (
        <>
          <MealBarGraph data={getGraphDatas[0].data.mealStatistics} />
          <MealCard data={getGraphDatas[0].data} graphType="week" />
        </>
      )}
      {currentCategory === 'Diaper' && (
        <>
          <DiaperBarGraph data={getGraphDatas[1].data.diaperStatistics} />
          <DiaperCard data={getGraphDatas[1].data} graphType="week" />
        </>
      )}
      {currentCategory === 'Sleep' && (
        <>
          <SleepBarGraph data={getGraphDatas[2].data.sleepStatistics} />
          <SleepCard data={getGraphDatas[2].data} graphType="week" />
        </>
      )}
      {currentCategory === 'Pump' && (
        <>
          <PumpingBreastBarGraph
            data={getGraphDatas[3].data.pumpingBreastStatistics}
          />
          <PumpCard data={getGraphDatas[3].data} graphType="week" />
        </>
      )}
      {currentCategory === 'Activity' && (
        <>
          <ActivityBarGraph data={getGraphDatas[4].data.activityStatistics} />
          <ActivityCard data={getGraphDatas[4].data} graphType="week" />
        </>
      )}
      {currentCategory === 'Health' && (
        <>
          <HealthBarGraph data={getGraphDatas[5].data.healthStatistics} />
          <HealthCard data={getGraphDatas[5].data} graphType="week" />
        </>
      )}
      {currentCategory === 'Extra' && (
        <>
          <ExtraBarGraph data={getGraphDatas[6].data.extraStatistics} />
          <ExtraCard data={getGraphDatas[6].data} graphType="week" />
        </>
      )}
    </GraphByWeekContainer>
  );
};

export { GraphByWeek };
