interface PageResponse<T> {
    totalPages: number;
    totalElements: number;
    number: number;
    size: number;
    numberOfElements: number;
    first: boolean;
    last: boolean;
    empty: boolean;
    content: Array<T>;
}