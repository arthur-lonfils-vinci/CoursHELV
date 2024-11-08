interface PageTitleProps {
  title: string;
}

const PageTitle = (props: PageTitleProps) => {
  return <h2 key={props.title}>{props.title}</h2>;
};

export default PageTitle;
