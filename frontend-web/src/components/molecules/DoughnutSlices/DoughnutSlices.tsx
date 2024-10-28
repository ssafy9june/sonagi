import { getArc } from '@/utils/doughnutUtils';
import { sectorHeight } from '@/constants/doughnutConstants';

interface DoughnutSliceProps {
  start: number;
  finished: number;
  color: string;
}

const DoughnutSlice = ({ start, finished, color }: DoughnutSliceProps) => (
  <path
    d={getArc(start, finished)}
    stroke={color}
    strokeWidth={sectorHeight}
    fill="transparent"
  />
);

export default DoughnutSlice;
