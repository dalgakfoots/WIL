import React, {Component} from "react";

class TOC extends Component {
    shouldComponentUpdate(nextProps, nextState, nextContext) {
        console.log("===> TOC shouldComponentUpdate"
        ,nextProps.data
            ,this.props.data
        );
        if (this.props.data === nextProps.data) {
            return false;
        }
        return true;
    }

    render() {
        console.log("===> TOC render")
        var lists = [];
        var data = this.props.data;
        var i = 0;

        while (i < data.length) {
            lists.push(
                <li key={data[i].id}>
                    <a
                        href={"/content/" + data[i].id}
                        data-id = {data[i].id}
                        onClick= {function (e) {
                            e.preventDefault();
                            this.props.onChangePage(e.target.dataset.id);
                        }.bind(this)}
                    >
                        {data[i].title}
                    </a>
                </li>);
            i = i + 1;
        }
        return (
            <nav>
                <ul>
                    {lists}
                </ul>
            </nav>
        );
    }
}

export default TOC;