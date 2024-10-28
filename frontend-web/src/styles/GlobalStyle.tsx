import { createGlobalStyle } from 'styled-components';

const GlobalStyle = createGlobalStyle`
    * {
        padding: 0;
        margin: 0;
        box-sizing: border-box;
        color: ${({ theme }) => theme.color.black1};
    }

    ul, ol, li {
        list-style: none;
    }

    body {
        width: 100vw;
        height: 100vh;
        font-family: 'Happiness-Sans', 'Inter', sans-serif;
        overflow: hidden;
    }

    .scrollable {
        /* height: calc(100vh - 17rem); */
        overflow: auto;
        -ms-overflow-style: none; /* IE and Edge */
        scrollbar-width: none; /* Firefox */
        &::-webkit-scrollbar {
            display: none;
        }
    }


    @keyframes fadeIn {
        from {
            opacity: 0;
        }
        to {
            opacity: 1;
        }
    }

    @-moz-keyframes fadeIn { /* Firefox */
        from {
            opacity: 0;
        }
        to {
            opacity: 1;
        }
    }

    @-webkit-keyframes fadeIn { /* Safari and Chrome */
        from {
            opacity: 0;
        }
        to {
            opacity: 1;
        }
    }   

    @keyframes fadeOut {
        from {
            opacity: 1;
        }
        to {
            opacity: 0;
        }
    }
    @-moz-keyframes fadeOut { /* Firefox */
        from {
            opacity: 1;
        }
        to {
            opacity: 0;
        }
    }
    @-webkit-keyframes fadeOut { /* Safari and Chrome */
        from {
            opacity: 1;
        }
        to {
            opacity: 0;
        }
    }
`;

export default GlobalStyle;
