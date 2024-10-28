import { useMutation } from '@tanstack/react-query';
import { deleteRecord } from '@/apis/Record/recordAPI';

interface RecordProps {
  type: string;
  recordId: number;
}

const useDeleteRecord = () => {
  return useMutation(({ type, recordId }: RecordProps) =>
    deleteRecord(type, recordId)
  );
};

export { useDeleteRecord };
