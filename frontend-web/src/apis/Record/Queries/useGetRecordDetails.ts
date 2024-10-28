import { useQuery } from '@tanstack/react-query';
import { getRecordDetails } from '@/apis/Record/recordAPI';

const useGetRecordDetails = (type: string, recordId: number) => {
  const { data } = useQuery(['recordDetails', recordId], () =>
    getRecordDetails(type, recordId)
  );
  return data;
};

export { useGetRecordDetails };
