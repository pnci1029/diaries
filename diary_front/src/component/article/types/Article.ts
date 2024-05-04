export interface ArticleSearchDto{
    title?: string;
    content?: string;
}
export interface ArticleResponseDto{
    articleIdx: BigInteger;
    title: string;
    content: string;
    views: number;
    createdAt: Date;
}

export interface ArticleStatus{

}