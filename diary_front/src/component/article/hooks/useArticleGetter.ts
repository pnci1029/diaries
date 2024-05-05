import {useCallback, useState} from "react";
import {getArticlesAsync} from "../../store/articleSlice";
import {ArticleResponseDto} from "../types/Article";
import {useAppDispatch} from "../../hooks/hooks";

interface ReturnType {
    // getArticles: (title: string) => Promise<ArticleResponseDto>;
    getArticles: ArticleResponseDto | undefined;
}

export function useArticleGetter(): { getArticles: () => any } {
    const dispatch = useAppDispatch();
    const [article, setArticle] = useState<ArticleResponseDto | undefined>();

    const getArticles = useCallback(
        async () => {
            dispatch(getArticlesAsync());
            const result: ArticleResponseDto = await dispatch(getArticlesAsync()).unwrap();

            return {
                ...result,
                // content: result.content.map((dto) => ({ ...dto } as ArticleResponseDto)),
            };
        },
        [dispatch]
    );

    console.log(getArticles)
    // const getArticles = useCallback(
    //     () => {
    //         return dispatch(getArticlesAsync())
    //             .unwrap()
    //             .then((result) => {
    //                 setArticle(result);
    //                 return result;
    //             });
    //     },
    //     [dispatch]
    // );

    return {
        getArticles,
    };
}