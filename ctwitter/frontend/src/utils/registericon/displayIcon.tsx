import { ArrowBack, Close } from "@mui/icons-material";

const ICON_SIZE = 25;

export const DisplayIcon = (step: number) => {
                switch (step) {
                    case 0:
                    case 1:
                        return <Close sx={{ fontSize: ICON_SIZE }} />;
                    case 2:
                    case 3:
                    case 5:
                        return <ArrowBack sx={{ fontSize: ICON_SIZE }} />;
                    case 4:
                        return <></>;
                    default:
                        return <></>;
                }
};
