import { addQuestion } from '@/apis/User/userAPI';
import { QuestionPost } from '@/types';
import { useMutation } from '@tanstack/react-query';

const useAddQuestion = () => {
  return useMutation((questionPost: QuestionPost) => addQuestion(questionPost));
};

export { useAddQuestion };
