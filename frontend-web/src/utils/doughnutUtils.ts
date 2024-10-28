import {
  xCenter,
  yCenter,
  radius,
  MAX_DEGREE,
  sectorPadding,
} from '@/constants/doughnutConstants';

export const getCoordsOnCircle = (degree: number) => {
  const radian = (degree / 180) * Math.PI;
  if (degree === 359.99) {
    console.log('hihi');
    console.log(xCenter + radius * Math.cos(radian));
  }
  return {
    x: xCenter + radius * Math.cos(radian),
    y: yCenter + radius * Math.sin(radian),
  };
};

export const getArc = (startDegree: number, finishDegree: number) => {
  const startCoord = getCoordsOnCircle(startDegree);
  const finishCoord = getCoordsOnCircle(finishDegree);
  const isLargeArc = finishDegree - startDegree > 180 ? 1 : 0;
  const isEnd = finishDegree === MAX_DEGREE;

  return `M ${startCoord.x} ${
    startCoord.y
  } A ${radius} ${radius} 0 ${isLargeArc} 1 ${finishCoord.x} ${finishCoord.y} ${
    isEnd ? 'Z' : ''
  }`;
};

export const timeStringToDegrees = (timeSections: string[]): number[] => {
  return timeSections.map(timeString => {
    const [hour, minute] = timeString.split(':').map(s => parseInt(s));
    const totalMinutes = (hour * 60 + minute) % 1440;
    return ((totalMinutes / 4) % 360) - 90;
  });
};

export const timeToDegreeAddPadding = (
  timeString: string
): [number, number] => {
  const [hour, minute] = timeString.split(':').map(s => parseInt(s));
  const totalM = hour * 60 + minute;
  const leftTotal = (totalM - sectorPadding + 1080) % 1440;
  const rightTotal = (totalM + sectorPadding + 1080) % 1440;
  return [leftTotal / 4, rightTotal / 4];
};
