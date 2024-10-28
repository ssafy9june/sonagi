import { useMutation } from '@tanstack/react-query';
import { updateRecord } from '@/apis/Record/recordAPI';
import { CombinedRecord } from '@/types/recordTypes';

// TODO: 타입지정
interface UpdateRecordInput {
  record: CombinedRecord;
  queryName: string;
}

const useUpdateRecord = () => {
  return useMutation(
    ({ record, queryName }: UpdateRecordInput) =>
      updateRecord(record, queryName),
    {
      onSuccess: () => {},
      onError: (err: Error) => {
        console.log(err);
      },
    }
  );
};

export { useUpdateRecord };
