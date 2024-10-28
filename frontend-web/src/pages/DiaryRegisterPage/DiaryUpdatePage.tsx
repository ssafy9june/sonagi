import { ChangeEvent, useState, useRef, useEffect, useCallback } from 'react';
import * as S from '@/pages/DiaryRegisterPage/DiaryRegisterPage.styles';
import DiaryRecorder from '@/components/molecules/DiaryRecorder/DiaryRecorder';
import { Image } from '@/components/atoms/Image/Image';
import IconPlusBlueRectDash from '@/assets/images/icon-plus-blue-rect-dash.png';
import { useNavigate, useParams } from 'react-router-dom';
import backArrow from '@/assets/images/icon-arrow-left-grey.png';
import Button from '@/components/atoms/Button/Button';
import { DiaryInfo, DiaryPutDto } from '@/types/diaryTypes';
import { useRecoilValue } from 'recoil';
import { BabiesOfUser, User } from '@/types';
import { userInfoState } from '@/states/userState';
import { selectedBabyState } from '@/states/babyState';
import { PATH } from '@/constants/path';
import { useUpdateDiary } from '@/apis/Diary/Mutations/useUpdateDiary';
import { selectedDateState } from '@/states/dateState';
import { diaryRecordList } from '@/states/diaryState';
import { useDeleteDiary } from '@/apis/Diary/Mutations/useDeleteDiary';

const DiaryUpdatePage: React.FC = () => {
  const navigate = useNavigate();
  const updateDiaryMutation = useUpdateDiary();
  const deleteDiaryMutation = useDeleteDiary();
  // const userInfo: User = useRecoilValue(userInfoState);
  // const babyInfo: BabiesOfUser = useRecoilValue(selectedBabyState);
  // const curDate: string = useRecoilValue(selectedDateState);
  const diaryInfoList: DiaryInfo[] = useRecoilValue(diaryRecordList);

  const { id } = useParams<{ id: string }>();
  const diaryInfo = diaryInfoList.find(e => e.diaryId === parseInt(id!, 10));

  type FileOrString = File | string;
  const [files, setFiles] = useState<FileOrString[]>(diaryInfo!.imgUrls);
  const [diaryContent, setDiaryContent] = useState<string>(diaryInfo!.content);
  const [removesFileState, setRemovesFileState] = useState<string[]>([]);

  const fileInputRefs = useRef<HTMLInputElement[]>([]);

  const RouteHandler = useCallback(() => navigate(-1), [navigate]);

  const handleSubmit = async () => {
    const formData = new FormData();

    const diaryPutDto: DiaryPutDto = {
      diaryId: parseInt(id!, 10),
      content: diaryContent,
      removeFiles: removesFileState,
    };

    for (let i = 0; i < files.length; i++) {
      formData.append('imgFiles', files[i]);
    }

    formData.append(
      'diaryPutDto',
      new Blob([JSON.stringify(diaryPutDto)], { type: 'application/json' })
    );

    await updateDiaryMutation.mutateAsync(formData);
    navigate(PATH.DIARY);
  };

  const handleRemove = () => {
    const userConfirmed = confirm('일기를 삭제 하시겠습니까 ?');
    if (userConfirmed) {
      deleteDiaryMutation.mutate(parseInt(id!, 10));
      navigate(PATH.DIARY);
    }
  };

  const handleFileChange = (
    event: ChangeEvent<HTMLInputElement>,
    index: number
  ) => {
    const fileList: File[] = event.target.files
      ? Array.from(event.target.files)
      : [];
    if (fileList.length !== 0) {
      setFiles(prevFiles => {
        const newFiles = [...prevFiles];
        newFiles[index] = fileList[0];
        return newFiles;
      });
      event.target.value = '';
    }
  };

  const handleImageClick = (index: number) => {
    if (fileInputRefs.current[index]) {
      fileInputRefs.current[index].click();
    }
  };

  const handleRemoveImage = (index: number, event: React.MouseEvent) => {
    event.stopPropagation();

    const obj = files[index];
    if (typeof obj === 'string') {
      setRemovesFileState(prev => [...prev, obj]);
    }

    setFiles(prevFiles => prevFiles.filter((_, i) => i !== index));
  };

  const addFileInputRef = (ref: HTMLInputElement | null, index: number) => {
    if (ref) {
      fileInputRefs.current[index] = ref;
    }
  };

  const handleDiaryContent = (data: string) => {
    setDiaryContent(data);
  };

  return (
    <>
      <S.DiaryRegisterContainer>
        <S.DiaryRegisterHeadContainer>
          <S.BackArrow onClick={RouteHandler}>
            <Image src={backArrow} width={1} />
          </S.BackArrow>
          <S.TitleText size="headSmall">육아일기 수정하기</S.TitleText>
        </S.DiaryRegisterHeadContainer>
        <S.DiaryRegisterBodyContainer>
          <DiaryRecorder
            onDataUpdate={handleDiaryContent}
            tContent={diaryContent}
          ></DiaryRecorder>
        </S.DiaryRegisterBodyContainer>
        <S.DiaryRegisterFileListContainer>
          {files.map((file, index) => (
            <S.DiaryRegisterWrapper key={index}>
              <Image
                src={
                  typeof file === 'string' ? file : URL.createObjectURL(file)
                }
                height={100}
                width={100}
                style={{ objectFit: 'cover' }}
                $unit="%"
                onClick={event => handleRemoveImage(index, event)}
              />
              <input
                type="file"
                onChange={event => {
                  handleFileChange(event, index);
                }}
                accept="image/*"
                ref={ref => addFileInputRef(ref, index)}
                style={{ display: 'none' }}
              />
            </S.DiaryRegisterWrapper>
          ))}
          {files.length < 4 && (
            <S.DiaryRegisterWrapper
              onClick={() => handleImageClick(files.length)}
            >
              <Image
                src={IconPlusBlueRectDash}
                height={100}
                width={100}
                $unit="%"
              />
              <input
                type="file"
                onChange={event => {
                  handleFileChange(event, files.length);
                }}
                accept="image/*"
                ref={ref => addFileInputRef(ref, files.length)}
                style={{ display: 'none' }}
              />
            </S.DiaryRegisterWrapper>
          )}
        </S.DiaryRegisterFileListContainer>
        <S.RegisterBtnContainer>
          <Button option="danger" onClick={handleRemove}>
            삭제하기
          </Button>
          <Button option="activated" onClick={handleSubmit}>
            수정하기
          </Button>
        </S.RegisterBtnContainer>
      </S.DiaryRegisterContainer>
    </>
  );
};

export default DiaryUpdatePage;
