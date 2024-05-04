import {useCallback, useState} from "react";
import {getArticlesAsync} from "../../store/articleSlice";
import {ArticleResponseDto} from "../types/Article";
import {useDispatch} from "react-redux";
import {MainApi} from "../../../api/MainApi";

interface ReturnType {
    // getArticles: (title: string) => Promise<ArticleResponseDto>;
    getArticles: ArticleResponseDto | undefined;
}

export function useArticleGetter(): { getArticles: () => any } {
    // const dispatch = useAppDispatch();
    const dispatch = useDispatch();
    const [article, setArticle] = useState<ArticleResponseDto | undefined>();

    const getArticles = useCallback(
        async () => {
            // const result: ArticleResponseDto = await dispatch(getArticlesAsync).unwrap();
            let result = getArticlesAsync();
            return {
                ...result,
                // content: result.content.map((dto) => ({ ...dto } as ArticleResponseDto)),
            };
        },
        [dispatch]
    );

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