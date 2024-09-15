export type StatusResponse = {
  msg: string;
  success: boolean;
  status: number;
};

export type PaginatedResult<T> = StatusResponse & {
  result: {
    hasMore: boolean;
    total: number;
    data: T[];
  };
};

export type SearchParams = {
  pageIndex: number;
  pageSize: number;
  [key: string]: string | number | undefined;
  // params?: string; // 一段JSON字符串
};

export type SearchResult<T> = PaginatedResult<T>;

export type AddParams = Array<Record<string, string>>;

export type UploadParams = {
  type: number;
  data: Record<string, string>;
};

export type DeleteParams = {
  OnlyKey: number;
  num: number;
};

export type ModifyParams = {
  type: number;
  id: number;
  params: string; // 一段JSON字符串
};
