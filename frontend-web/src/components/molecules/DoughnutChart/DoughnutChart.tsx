import theme from '@/styles/theme';
import { categoryToColorMap } from '@/constants/categoryToColorMap';
import { useRecoilValue } from 'recoil';
import { selectedCategoryState } from '@/states/categoryState';

import {
  xCenter,
  yCenter,
  VIEW_BOX,
  textRadius,
  sectorHeight,
} from '@/constants/doughnutConstants';

import {
  timeStringToDegrees,
  timeToDegreeAddPadding,
  getArc,
} from '@/utils/doughnutUtils';

import DoughnutSlice from '@/components/molecules/DoughnutSlices/DoughnutSlices';
import { PATH } from '@/constants/path';
import { ReactNode } from 'react';

interface TimeProps {
  createdTime: string;
  endTime?: string;
}

interface DoughnutChartProps {
  data: TimeProps[];
}

const DoughnutChart = ({ data }: DoughnutChartProps) => {
  // numbers도 따로 molcules로 빼고 싶었지만 ... JSX의 svg Elements 관련 지식 무지로 보류
  let numbers: ReactNode[] = [];

  for (let i = 0; i <= 23; i++) {
    const angle = ((i * 15 - 90) * Math.PI) / 180;
    const x = xCenter + textRadius * Math.cos(angle);
    const y = yCenter + textRadius * Math.sin(angle);
    let displayText = i.toString();

    if (i % 2 === 1) {
      displayText = '•';
    }

    const svgTextElement = (
      <text
        fontSize="9px"
        fill={theme.color.gray1}
        key={i}
        x={x}
        y={y}
        textAnchor="middle"
        alignmentBaseline="middle"
      >
        {displayText}
      </text>
    );

    numbers = [...numbers, svgTextElement];
  }

  const currentCategory = useRecoilValue(selectedCategoryState(PATH.GRAPH));
  const currentColor = categoryToColorMap[currentCategory];

  // 시간이 2개 있는지 확인하는 함수
  const isTimeItem2 = (timeObj: TimeProps[]) => {
    return timeObj.length > 0 && 'endTime' in timeObj[0];
  };

  return (
    <svg viewBox={VIEW_BOX}>
      {numbers}
      <path
        d={getArc(0, 359.99)}
        stroke={theme.color.lightgrey}
        fill="transparent"
        strokeWidth={sectorHeight}
      />
      {isTimeItem2(data) &&
        // TODO: 23:59 ~ 00:00
        data.map(record => {
          console.log('heheheheh', data);
          const timeSections: string[] = [record.createdTime, record.endTime!];
          const [start, finished] = timeStringToDegrees(timeSections);

          return (
            <path // 구간 도넛
              key={record.createdTime}
              d={getArc(start, finished)}
              stroke={theme.color[currentColor]}
              fill="transparent"
              strokeWidth={sectorHeight}
            />
          );
        })}

      {!isTimeItem2(data) &&
        data
          .map(key => key.createdTime)
          .map(timeToDegreeAddPadding)
          .map((degree, index) => (
            <DoughnutSlice
              key={index}
              start={degree[0]}
              finished={degree[1]}
              color={theme.color[currentColor]}
            />
          ))}
    </svg>
  );
};

export default DoughnutChart;
