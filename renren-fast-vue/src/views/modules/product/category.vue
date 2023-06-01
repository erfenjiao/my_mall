<!--  -->
<template>
    <div>
    <!-- 菜单数据赋值给menus -->
    <el-tree :data="menus" show-checkbox node-key="catId"                 
        :props="defaultProps" @node-click="handleNodeClick" 
        :expand-on-click-node="false"
        :default-expanded-keys="expandedKey">

        <span class="custom-tree-node" slot-scope="{ node, data }">
            <span>{{ node.label }}</span>
            <span>
            <el-button 
                v-if="node.level <= 2"
                type="text"
                size="mini"
                @click="() => append(data)">
                Append
            </el-button>
            <el-button 
                type="text"
                size="mini"
                @click="() => edit(data)">
                Edit
            </el-button>
            <el-button
                v-if="node.childNodes.length == 0"
                type="text"
                size="mini"
                @click="() => remove(node, data)">
                Delete
            </el-button>
            </span>
        </span>

    </el-tree>

    <el-dialog title="提示" :visible.sync="dialogVisible" width="30%">
      <el-form :model="category">
        <el-form-item label="分类名称">
          <el-input v-model="category.name" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addCategory = false">确 定</el-button>
      </span>
    </el-dialog>

    <el-dialog :title="title" :visible.sync="dialogVisible" width="30%">
      <el-form :model="category">
        <el-form-item label="分类名称">
          <el-input v-model="category.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="图标">
          <el-input v-model="category.inco" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="计量单位">
          <el-input
            v-model="category.productUnit"
            autocomplete="off"
          ></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitData">确 定</el-button>
      </span>
    </el-dialog>

    </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from '《组件路径》';

export default {
    data() {
      return {
        title: "",
        dialogType: "",
        category:{name:"", parentCid:0, catLevel:0, showStatus:1, sort:0, inco:"", productUnit:"", catId:null},
        dialogVisible: false,
        menus: [],
        expandedKey:[],
        defaultProps: {
          children: 'children',   // 子节点
          label: 'name'           
        }, 
      };
    },
    methods: {
        handleNodeClick(data) {
            console.log(data);
        },

        // 获取菜单数据
        getMenus() {
            this.$http({
            url: this.$http.adornUrl('/product/category/list/tree'),
            method: 'get',
            }).then(({data})=>{
                console.log("成功获取到菜单数据", data.data)
                this.menus = data.data     // 菜单数据赋值给menus
            })
        },
       
        append(data) {
            this.dialogType = "add";
            this.title = "添加菜单";
            console.log("append", data);
            this.dialogVisible = true;
            this.category.parentCid = data.catId;
            this.category.catLevel = data.catLevel * 1 + 1;
            this.category.name = "",
            this.category.inco = "",
            this.category.productUnit = ""
        },

        addCategory() {
            console.log("提交的数据", this.category);
            this.$http({
                url: this.$http.adornUrl("/product/category/save"),
                method: "post",
                data: this.$http.adornData(this.category, false),
            }).then(({ data }) => {
                this.$message({
                    message: "添加成功",
                    type: "success",
                    });
                    //刷新出新的菜单
                    this.getMenus();
                    //设置需要默认展开的菜单
                    this.expandedKey = [this.category.parentCid];
                    this.dialogVisible = false;
            })
        },

        submitData() {
            if (this.dialogType == "add") {
                this.addCategory();
            }
            if (this.dialogType == "edit") {
                this.editCategory();
            }
        },

        edit(data) {
            this.dialogType = "edit";
            this.title = "修改菜单";
            this.dialogVisible = true;

            // 发送请求获取当前节点最新的数据
            this.$http({
                url: this.$http.adornUrl(`/product/category/info/${data.catId}`),
                method: "get",
            }).then(({ data }) => {
                //请求成功
                console.log(data);
                this.category.catId = data.data.catId;
                this.category.name = data.data.name;
                this.category.inco = data.data.inco;
                this.category.productUnit = data.data.productUnit;
                this.category.parentCid = data.data.parentCid;
                this.category.catLevel = data.data.catLevel;
                this.category.showStatus = data.data.showStatus;
            });
        },

        //绑定对话框的确定按钮，向后台发送更新请求，传过去想要修改的字段
        editCategory() {
            var { catId, name, inco, productUnit } = this.category;
            this.$http({
                url: this.$http.adornUrl("/product/category/update"),
                method: "post",
                data: this.$http.adornData({ catId, name, inco, productUnit }, false),  
            }).then(({ data }) => {
                this.$message({
                    message: "修改成功",
                    type: "success",
                });
                // 刷新出新的菜单
                this.getMenus();
                // 设置需要默认展开的菜单
                this.expandedKey = [this.category.parentCid];
                // 关闭对话框
                this.dialogVisible = false;
            });
        },


        remove(node, data) {
                var ids = [data.catId];
                this.$confirm(`是否删除【${data.name}】菜单？`, "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning",
                })
                .then(() => {
                this.$http({
                    url: this.$http.adornUrl("/product/category/delete"),
                    method: "post",
                    data: this.$http.adornData(ids, false),
                }).then(({ data }) => {
                    this.$message({
                    message: "菜单删除成功",
                    type: "success",
                    });
                    //刷新出新的菜单
                    this.getMenus();
                    //设置需要默认展开的菜单
                    this.expandedKey = [node.parent.data.catId]
                });
                })
                .catch(() => {});
            }
    },
//import引入的组件需要注入到对象中才能使用
components: {},

//监听属性 类似于data概念
computed: {},
//监控data中的数据变化
watch: {},
//方法集合

//生命周期 - 创建完成（可以访问当前this实例）
created() {
    this.getMenus();   //在创建时需要获取菜单数据
},
//生命周期 - 挂载完成（可以访问DOM元素）
mounted() {

},
beforeCreate() {}, //生命周期 - 创建之前
beforeMount() {}, //生命周期 - 挂载之前
beforeUpdate() {}, //生命周期 - 更新之前
updated() {}, //生命周期 - 更新之后
beforeDestroy() {}, //生命周期 - 销毁之前
destroyed() {}, //生命周期 - 销毁完成
activated() {}, //如果页面有keep-alive缓存功能，这个函数会触发
}
</script>
<style scoped>

</style>