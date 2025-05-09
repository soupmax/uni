document.addEventListener("DOMContentLoaded", function () {
	const fcaptions = document.getElementsByTagName("figcaption");
	if (fcaptions.length > 0) {
		for (const fcap of fcaptions) {
			let txt = "Ich bin ein wichtiger Beispieltext.\n\n";
			for (let i = 0; i < 20; i++) {
				txt += "Ich bin ein Beispieltext. ";
			}
			fcap.innerText = txt;
		}
	}
});
