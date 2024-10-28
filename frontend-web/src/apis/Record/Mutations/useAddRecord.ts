// mutation을 수행하는 custom Hook
import { useMutation } from '@tanstack/react-query';
import { AllRecords } from '@/types/recordTypes';
import { addRecord } from '@/apis/Record/recordAPI';

type UseAddRecordTypeProps = AllRecords & {
  type: string;
};

const useAddRecord = () => {
  return useMutation(
    ({ type, ...recordAttributes }: UseAddRecordTypeProps) =>
      addRecord(type, recordAttributes),
    {
      onSuccess: () => {},
      onError: (err: Error) => {
        console.log(err);
      },
    }
  );
};

export { useAddRecord };
