const path = require("path");

module.exports = {
	entry: "./src/index.html",
	output: {
		filename: "bundle.js",
		path: path.resolve(__dirname, "dist"),
	},
	mode: "development",
};
